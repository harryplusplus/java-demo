#!/usr/bin/env bash

set -ex

cat /etc/os-release
uname -r
whoami

sudo apt update
sudo apt install -y curl
sudo apt install -y git
sudo apt install -y wget
# Use xdg-open to forward URLs to the host system.
sudo apt install -y xdg-utils

echo 'alias ll="ls -a -p -lh"' >> ~/.bashrc
echo >> ~/.bashrc

curl -s "https://get.sdkman.io?ci=true" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk version

sdk install java 21.0.2-open
java --version

sdk install gradle 8.14.3
gradle --version

bash .devcontainer/install_k6.sh
k6 --version
