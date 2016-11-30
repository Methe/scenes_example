package com.example.matheus.scenesexample.statemachine;

import java.io.Serializable;

/**
 * Any object that implements this key can be used as a key to a state. Normally this is used in
 * enums so that keys are singletons.
 */
public interface StateKey extends Serializable {}
