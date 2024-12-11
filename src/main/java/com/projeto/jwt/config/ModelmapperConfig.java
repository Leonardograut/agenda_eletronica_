package com.projeto.jwt.config;

import org.modelmapper.ModelMapper;

public class ModelmapperConfig {

    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;
    }


}
