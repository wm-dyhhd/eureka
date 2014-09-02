/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.eureka.server.transport.registration.asynchronous;

import com.netflix.eureka.transport.EurekaTransports;
import com.netflix.eureka.transport.EurekaTransports.Codec;
import com.netflix.karyon.transport.tcp.KaryonTcpModule;
import io.reactivex.netty.servo.ServoEventsListenerFactory;

/**
 * @author Tomasz Bak
 */
public class AvroRegistrationModule extends KaryonTcpModule<Object, Object> {

    public AvroRegistrationModule() {
        super("avroRegistrationServer", Object.class, Object.class);
    }

    @Override
    protected void configureServer() {
        bindPipelineConfigurator().toInstance(EurekaTransports.registrationPipeline(Codec.Avro));
        bindConnectionHandler().to(AsyncRegistrationHandler.class);
        bindEventsListenerFactory().to(ServoEventsListenerFactory.class);
        server().port(7002);
    }
}