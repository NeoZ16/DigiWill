package de.digiwill.service;

import de.digiwill.model.EmailResponseHandle;
import de.digiwill.repository.EmailResponseHandleRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class EmailResponseHandleManager {

    @Autowired
    private EmailResponseHandleRepository emailResponseHandleRepository;
    @Autowired
    private UserHandleManager userHandleManager;

    public EmailResponseHandle findEmailResponseHandleBy(ObjectId UID){
        EmailResponseHandle emailResponseHandle = emailResponseHandleRepository.findEmailResponseHandleBy(UID);
        if(emailResponseHandle.getTimeout() < Instant.now().getEpochSecond()){
            emailResponseHandle.executeTimeout(userHandleManager, this);
            emailResponseHandle = null;
        }
        return emailResponseHandle;
    }

    public void deleteEmailResponseHandle(EmailResponseHandle emailResponseHandle){
        emailResponseHandleRepository.delete(emailResponseHandle);
    }

    public void deleteEmailResponseHandle(ObjectId UID){
        emailResponseHandleRepository.deleteEmailResponseHandleBy(UID);
    }

    public List<EmailResponseHandle> findAll(){
        return emailResponseHandleRepository.findAll();
    }
}