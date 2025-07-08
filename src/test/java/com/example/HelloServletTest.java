package com.example;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class HelloServletTest {

     @Test
    public void testDoGet() throws Exception {
        // Mock Servlet objects
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Mock PrintWriter to capture output
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);

        // Create and call doGet
        HelloServlet servlet = new HelloServlet();
        servlet.doGet(request, response);

        // Verify behavior
        Mockito.verify(response).setContentType("text/html");

        // Check output content
        writer.flush();
        String output = stringWriter.toString();
        assertTrue(output.contains("<h1>Hello, World, I am a servlet, chú ý nhánh của dangluong-17.06.2025!</h1>"),
                "Output does not contain expected heading");
        assertTrue(output.contains("<p>Alo alo</p>"),
                "Output does not contain expected paragraph");
        assertTrue(output.contains("<a href='http://localhost:8082/java-servlet-web-1.0-SNAPSHOT/'><button>Back to Index</button></a>"),
                "Output does not contain back button");
    }
}