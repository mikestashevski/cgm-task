package com.cgm.task.services;

import com.cgm.task.model.OrmProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final OrmProxy ormProxy;

    public long authorizeDoctor(String authorizationHeader) {
        String[] usernamePasswordHash = authorizationHeader.replace("Basic ", "").split(":");
        String username = usernamePasswordHash[0];
        String passwordHash = usernamePasswordHash[1];
        return ormProxy.getDoctors().where(
                        doctorTable -> doctorTable.getProperty("username", String.class).equals(username)
                                && doctorTable.getProperty("password_hash", String.class).equals(passwordHash))
                .first().getProperty("id", BigInteger.class).longValue();
    }
}
