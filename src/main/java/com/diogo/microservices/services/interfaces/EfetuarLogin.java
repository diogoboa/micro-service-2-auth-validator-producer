package com.diogo.microservices.services.interfaces;

import com.diogo.microservices.commands.in.EfetuarLoginDeUsuarioCommandIn;
import com.diogo.microservices.commands.out.EfetuarLoginDeUsuarioCommandOut;

public interface EfetuarLogin {
    EfetuarLoginDeUsuarioCommandOut executar(EfetuarLoginDeUsuarioCommandIn command);
}
