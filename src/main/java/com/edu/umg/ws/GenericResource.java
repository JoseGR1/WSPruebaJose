package com.edu.umg.ws;

import com.edu.umg.dao.StudentDAO;
import com.edu.umg.entities.Student;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("students")
public class GenericResource {

    @Context
    private UriInfo context;

    private StudentDAO studentDAO = new StudentDAO();

    public GenericResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents() {
        return studentDAO.getAllStudents();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentDAO.getStudentById(id);
        if (student != null) {
            return Response.ok(student).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(Student student) {
        studentDAO.addStudent(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") Long id, Student student) {
        Student existingStudent = studentDAO.getStudentById(id);
        if (existingStudent != null) {
            student.setId(id); // Ensure the ID is preserved
            studentDAO.updateStudent(student);
            return Response.ok(student).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        Student existingStudent = studentDAO.getStudentById(id);
        if (existingStudent != null) {
            studentDAO.deleteStudent(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
