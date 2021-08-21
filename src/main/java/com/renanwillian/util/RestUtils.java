package com.renanwillian.util;

import javax.ws.rs.core.Response;
import java.util.List;

public class RestUtils {

    private RestUtils() {}

    public static Response okOrNoContent(Object response) {
        if (response == null) return Response.noContent().build();
        return Response.ok(response).build();
    }

    public static Response okOrNoContent(List<?> response) {
        if (response == null || response.isEmpty()) return Response.noContent().build();
        return Response.ok(response).build();
    }
}