package com.diogo.microservices.services.interfaces.usuario;

import com.diogo.microservices.services.commands.in.EfetuarLoginDeUsuarioCommandIn;
import com.diogo.microservices.services.commands.out.EfetuarLoginDeUsuarioCommandOut;

public interface EfetuarLogin {
    EfetuarLoginDeUsuarioCommandOut executar(EfetuarLoginDeUsuarioCommandIn command);
}
