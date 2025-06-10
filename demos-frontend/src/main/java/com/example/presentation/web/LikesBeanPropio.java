package com.example.presentation.web;

import java.io.Serializable;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

@Stateful
@SessionScoped
public class LikesBeanPropio implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private int hits = 1;

    // Increment and return the number of hits
	public int getHits() {
        return hits++;
    }
    
	@Remove
    public void remove() {
    }
}
