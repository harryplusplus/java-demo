#!/usr/bin/env bash

set -ex

cat /etc/os-release
uname -r

source "/home/vscode/.sdkman/bin/sdkman-init.sh"
sdk version
java --version
gradle --version

.devcontainer/install_k6.sh
k6 --version
