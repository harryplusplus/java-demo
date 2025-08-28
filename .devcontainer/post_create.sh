#!/usr/bin/env bash

set -ex

cat /etc/os-release
uname -r

sdk version
java --version
gradle --version
