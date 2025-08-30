#!/usr/bin/env bash

set -ex

ARCH=$(uname -m)
K6_ARCH=""
if [[ "$ARCH" == "x86_64" ]]; then
    K6_ARCH="amd64"
elif [[ "$ARCH" == "aarch64" ]]; then
    K6_ARCH="arm64"
else
    echo "Unsupported Architecture. architecture: $ARCH"
    exit 1
fi

if [[ "$K6_ARCH" == "" ]]; then
    echo "Unexpected k6 Architecture."
    exit 1
fi

TEMP_DIR=$(mktemp -d)
cd "$TEMP_DIR"

curl -L "https://github.com/grafana/k6/releases/download/v1.2.3/k6-v1.2.3-linux-$K6_ARCH.tar.gz" -o k6.tar.gz
tar -xzvf k6.tar.gz --strip-components=1
mkdir -p "$HOME/.local/bin"
mv k6 "$HOME/.local/bin/"

rm -r "$TEMP_DIR"
