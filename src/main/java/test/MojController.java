package test;

import anotacije.Controller;
import anotacije.GET;
import anotacije.POST;
import anotacije.Path;

@Controller
public class MojController {
    @GET
    @Path("/test")
    public void getTest() {
        System.out.println("TEST");
    }

    @POST
    @Path("/create")
    public void createResource() {
        System.out.println("CREATE");
    }
}
