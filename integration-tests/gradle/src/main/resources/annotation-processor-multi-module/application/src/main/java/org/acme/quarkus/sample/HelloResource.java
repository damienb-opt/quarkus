package org.acme.quarkus.sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.common.CarMapper;
import org.acme.common.domain.Car;
import org.acme.common.domain.CarDTO;

@Path("/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CarDTO hello() {
        Car car = new Car("foo", 4);
        return CarMapper.INSTANCE.carToCarDTO(car);
    }
}