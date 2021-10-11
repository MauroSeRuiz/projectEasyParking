<%-- 
    Document   : peticionesCliente
    Created on : 10-Oct-2021, 07:12:43
    Author     : maurosebastianruiz
--%>

<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="logica.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String respuesta = "{";
    String proceso = request.getParameter("proceso");
    Cliente c = new Cliente();
    switch(proceso){
        case "guardarCliente":
            System.out.println("Entro a guardar contacto");
            
            c.setPlaca(request.getParameter("placa"));
            c.setTipoVe(request.getParameter("tipoVeh"));
            c.setIdpark(request.getParameter("idpark"));
            c.setCliente(request.getParameter("cliente"));
            c.setIdentificacion(request.getParameter("identificacion"));
            c.setEstado(request.getParameter("estado"));
            
            if (c.guardarCliente()){
             respuesta += "\"" + proceso + "\": true";  // el \ se usa para concatenar en json indicando que se hizo el proceso (true)
            } else {
             respuesta += "\"" + proceso + "\": false";  // el \ se usa para concatenar en json indicando que NO se hizo el proceso (false)
            }
            break;
            
        case "actualizarCliente":
            System.out.println("Actualizar contacto");
            //Cliente c = new Cliente();
            c.setPlaca(request.getParameter("placa"));
            c.setTipoVe(request.getParameter("tipoVeh"));
            c.setIdpark(request.getParameter("idpark"));
            c.setCliente(request.getParameter("cliente"));
            c.setIdentificacion(request.getParameter("identificacion"));
            c.setEstado(request.getParameter("estado"));
            
            if (c.actualizarCliente(request.getParameter("placa"))){
             respuesta += "\"" + proceso + "\": true";  // el \ se usa para concatenar en json indicando que se hizo el proceso (true)
            } else {
             respuesta += "\"" + proceso + "\": false";  // el \ se usa para concatenar en json indicando que NO se hizo el proceso (false)
            }
            break;   
            
        case "listarCliente":
            System.out.println("listarCliente");
            List<Cliente> listaCliente =c.listarCliente();
            if(listaCliente.isEmpty()){
              respuesta += "\"" + proceso + "\": false,\"Cliente\":[]";  // el \ se usa para concatenar en json indicando que se hizo el proceso (true)
            } else {
             respuesta += "\"" + proceso + "\": true,\"Cliente\":" + new Gson().toJson(listaCliente);  // el \ se usa para concatenar en json indicando que NO se hizo el proceso (false)
            }
                
        break;
        default:
            respuesta += "\"ok\": false,";
            respuesta += "\"error\": \"INVALID\",";
            respuesta += "\"errorMsg\": \"Lo sentimos, los datos que ha enviado,"
                    + " son inválidos. Corrijalos y vuelva a intentar por favor.\"";
    }
    respuesta += "}";
    response.setContentType("application/json;charset=iso-8859-1");
    out.print(respuesta);
    System.out.println(respuesta);
       
    }


%>