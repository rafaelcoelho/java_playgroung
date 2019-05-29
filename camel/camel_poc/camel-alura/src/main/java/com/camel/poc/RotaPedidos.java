package com.camel.poc;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaPedidos
{

    public static void main(String[] args) throws Exception
    {

        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder()
        {

            @Override
            public void configure() throws Exception
            {
                from("file:pedidos?delay=5s&noop=true")
                        .setProperty("clientId", xpath("/pedido/pagamento/email-titular/text()"))
                        .setProperty("orderId", xpath("/pedido/id/text()"))
                        .split().xpath("/pedido/itens/item")
                        .filter().xpath("/item/formato[text()='EBOOK']")
                        .setProperty("ARQ", xpath("/item/livro/codigo/text()"))
                        .removeProperties(xpath("/livro/valorEbook").getText())
                        .marshal()
                        .xmljson()
                        .log("${body}")
                        .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                        .setHeader(Exchange.HTTP_QUERY, simple("ebookId=${property.ARQ}&pedidoId=${property.orderId}&clienteId=${property.clientId}"))
                        .to("http4://localhost:8080/webservices/ebook/item");
            }

        });

        context.addRoutes(new RotaHttpPollingNegociacoes());

        context.start();
        Thread.sleep(20000);
        context.stop();
    }
}
