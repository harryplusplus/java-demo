FROM mcr.microsoft.com/devcontainers/base:debian-12

ENV DEBIAN_FRONTEND=noninteractive

SHELL ["/bin/bash", "-c"]

RUN apt-get update && \
    apt-get install -y \
    curl \
    git \
    wget \
    # Use xdg-open to forward URLs to the host system.
    xdg-utils \
    && apt clean && \
    rm -rf /var/lib/apt/lists/*

USER vscode
RUN echo 'alias ll="ls -a -p -lh"' >> ~/.bashrc

RUN curl -s "https://get.sdkman.io?ci=true" | bash && \
    source "/home/vscode/.sdkman/bin/sdkman-init.sh" && \
    sdk install java 21.0.2-open && \
    sdk install gradle 8.14.3

WORKDIR /work
