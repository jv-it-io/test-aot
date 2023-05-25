#!/usr/bin/env bash
rm -rf target
./mvnw -Pnative clean native:compile
#./gradlew nativeCompile