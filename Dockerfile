FROM rust:1.67.1-buster

RUN apt-get update
RUN apt-get install -y valgrind

WORKDIR /algorithms

COPY Cargo.toml ./
COPY Cargo.lock ./
COPY src ./src
COPY examples ./examples
COPY tests ./tests
COPY docker/* ./

ENTRYPOINT ["/bin/bash"]