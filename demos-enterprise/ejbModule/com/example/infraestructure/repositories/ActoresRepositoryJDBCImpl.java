package com.example.infraestructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.example.contracts.domain.repositories.ActoresRepository;
import com.example.core.annotation.Repository;
import com.example.core.contracts.domain.repositories.Page;
import com.example.core.contracts.domain.repositories.PageModel;
import com.example.core.domain.exceptions.NotFoundException;
import com.example.domain.entities.Actor;

@Stateless
@Alternative
public class ActoresRepositoryJDBCImpl implements ActoresRepository {
//    @Resource(name = "SakilaDataSource", lookup = "SakilaDataSource")
	private DataSource ds;

	public ActoresRepositoryJDBCImpl() {
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("SakilaDataSource");
		} catch (NamingException ex) {
			throw new RuntimeException("Error initializing ActorRepository");
		}
	}

	@Override
	public List<Actor> findAll() {
		List<Actor> rslt = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(
					"SELECT actor_id,first_name,last_name,last_update FROM actor ORDER BY first_name, last_name");
			while (rs.next()) {
				rslt.add(new Actor(rs.getShort("actor_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getDate("last_update")));
			}
			rs.close();
			cmd.close();
		} catch (SQLException e) {
			throw new RuntimeException("SQLException", e);
		}
		return rslt;
	}

	@Override
	public PageModel<Actor> findAll(Page page) {
		// Ejemplo de mala practica
		return null;
	}

	@Override
	public long count() {
		// Ejemplo de mala practica
		return 0;
	}

	@Override
	public Optional<Actor> findById(Integer id) {
		try (Connection con = ds.getConnection()) {
			PreparedStatement cmd = con
					.prepareStatement("SELECT actor_id,first_name,last_name,last_update FROM actor WHERE actor_id=?");
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();
			if (rs.next())
				return Optional.of(new Actor(rs.getShort("actor_id"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getDate("last_update")));
		} catch (SQLException e) {
			throw new RuntimeException("SQLException", e);
		}
		return Optional.empty();
	}

	@Override
	public Actor add(Actor item) {
		try (Connection con = ds.getConnection()) {
			PreparedStatement cmd = con.prepareStatement("INSERT INTO actor(first_name, last_name) VALUES(?,?)",
					new String[] { "actor_id" });
			cmd.setString(1, item.getFirstName());
			cmd.setString(2, item.getLastName());
			cmd.executeUpdate();
			ResultSet rs = cmd.getGeneratedKeys();
			rs.next();
			return findById(rs.getInt(1)).get();
		} catch (SQLException e) {
			throw new RuntimeException("SQLException", e);
		}
	}

	@Override
	public Actor modify(Actor item) throws NotFoundException {
		try (Connection con = ds.getConnection()) {
			PreparedStatement cmd = con.prepareStatement(
					"UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id= ?", new String[] { "last_update" });
			cmd.setString(1, item.getFirstName());
			cmd.setString(2, item.getLastName());
			cmd.setInt(3, item.getActorId());
			if (cmd.executeUpdate() == 0)
				throw new NotFoundException();
		} catch (SQLException e) {
			throw new RuntimeException("SQLException", e);
		}
		return item;
	}

	@Override
	public void deleteById(Integer id) throws NotFoundException {
		try (Connection con = ds.getConnection()) {
			PreparedStatement cmd = con.prepareStatement("DELETE FROM actor WHERE actor_id=?");
			cmd.setInt(1, id);
			if (cmd.executeUpdate() == 0)
				throw new NotFoundException();
		} catch (SQLException e) {
			throw new RuntimeException("SQLException", e);
		}
	}

	@Override
	public void delete(Actor item) throws NotFoundException {
		if (item == null)
			throw new NotFoundException();
		deleteById(item.getActorId());
	}

}
