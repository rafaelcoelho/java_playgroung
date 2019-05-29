//**********************************************************************
// Copyright (c) 2019 Telefonaktiebolaget LM Ericsson, Sweden.
// All rights reserved.
// The Copyright to the computer program(s) herein is the property of
// Telefonaktiebolaget LM Ericsson, Sweden.
// The program(s) may be used and/or copied with the written permission
// from Telefonaktiebolaget LM Ericsson or in accordance with the terms
// and conditions stipulated in the agreement/contract under which the
// program(s) have been supplied.
// **********************************************************************
package com.camel.poc;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xstream.XStreamDataFormat;

import com.thoughtworks.xstream.XStream;

public class RotaHttpPollingNegociacoes extends RouteBuilder
{

    @Override
    public void configure() throws Exception
    {
        final XStream xstream = new XStream();
        xstream.alias("negociacao", Negociacao.class);

        from("timer://negociacoes?fixedRate=true&delay=1s&period=360s")
                .to("http4://argentumws-spring.herokuapp.com/negociacoes")
                .convertBodyTo(String.class)
                .unmarshal(new XStreamDataFormat(xstream))
                .split(body())
                .log("${body}")
                .end();
        //                .setHeader(Exchange.FILE_NAME, constant("dasda.xml"))
        //                .to("file:saida");
    }

}
