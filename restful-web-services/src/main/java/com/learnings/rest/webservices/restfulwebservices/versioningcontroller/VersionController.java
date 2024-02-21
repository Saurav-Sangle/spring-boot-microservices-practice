package com.learnings.rest.webservices.restfulwebservices.versioningcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    //below two are URI Versioning option
    @GetMapping(path = "/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Saurav Sangle");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Saurav", "Sangle"));
    }

    //below on request parameter
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getPersonBasedOnReqParamVersionOne() {
        return new PersonV1("Saurav Sangle");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getPersonBasedOnReqParamVersionTwo() {
        return new PersonV2(new Name("Saurav", "Sangle"));
    }

    //below on request header
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getPersonBasedOnReqHeaderOne() {
        return new PersonV1("Saurav Sangle");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getPersonBasedOnReqHeaderTwo() {
        return new PersonV2(new Name("Saurav", "Sangle"));
    }

    //below on Media Type Accept header
    @GetMapping(path = "/person/header", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonBasedOnAcceptHeaderOne() {
        return new PersonV1("Saurav Sangle");
    }

    @GetMapping(path = "/person/header", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonBasedOnAcceptHeaderTwo() {
        return new PersonV2(new Name("Saurav", "Sangle"));
    }
}
