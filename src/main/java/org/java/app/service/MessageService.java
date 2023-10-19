package org.java.app.service;

import org.java.app.pojo.Message;
import org.java.app.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	@Autowired
	private MessageRepo messageRepo;
	
	public void save(Message message) {
		messageRepo.save(message);
	}
}
