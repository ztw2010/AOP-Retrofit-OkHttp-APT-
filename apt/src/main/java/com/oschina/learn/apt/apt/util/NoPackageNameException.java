package com.oschina.learn.apt.apt.util;

import javax.lang.model.element.TypeElement;

public class NoPackageNameException extends Exception {

    public NoPackageNameException(TypeElement typeElement) {
        super("The package of " + typeElement.getSimpleName() + " has no name");
    }
}
