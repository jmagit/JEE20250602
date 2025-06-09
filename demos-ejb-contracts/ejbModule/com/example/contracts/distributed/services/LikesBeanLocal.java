package com.example.contracts.distributed.services;

import javax.ejb.Local;

@Local
public interface LikesBeanLocal {

	// Increment and return the number of hits
	int getHits();

	void remove();

}