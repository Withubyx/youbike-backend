package nl.novi.youbike_api.controller.helper;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class UriHelper{

    private UriHelper() {}

    public static URI buildUri(Object id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    public static URI buildUri(String uriMiddlePart, Object id) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(uriMiddlePart + "/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
