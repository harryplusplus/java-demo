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

# sdk
curl -s "https://get.sdkman.io?ci=true" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk version

# java
sdk install java 21.0.2-open
java --version

# gradle
sdk install gradle 8.14.3
gradle --version

# k6
bash .devcontainer/install_k6.sh
k6 --version

# brew
NONINTERACTIVE=1 /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
echo >> "$HOME/.bashrc"
echo 'eval "$(/home/linuxbrew/.linuxbrew/bin/brew shellenv)"' >> "$HOME/.bashrc"
eval "$(/home/linuxbrew/.linuxbrew/bin/brew shellenv)"
sudo apt install -y build-essential
brew install gcc
brew --version

# ktlint
brew install ktlint
ktlint --version
