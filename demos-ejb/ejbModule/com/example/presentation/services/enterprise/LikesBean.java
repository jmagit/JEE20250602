package com.example.presentation.services.enterprise;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import com.example.contracts.distributed.services.LikesBeanLocal;
import com.example.contracts.distributed.services.LikesBeanRemote;

@Stateful
@SessionScoped
public class LikesBean implements LikesBeanRemote, LikesBeanLocal {
    private int hits = 1;

    // Increment and return the number of hits
    @Override
	public int getHits() {
        return hits++;
    }
    
    @Override
	@Remove
    public void remove() {
    }
}
