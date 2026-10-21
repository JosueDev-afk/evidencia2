#!/usr/bin/env bash
# Compila el proyecto y genera el JAR ejecutable en bin/
set -euo pipefail
cd "$(dirname "$0")"

VER=2.24.3
CP="lib/log4j-api-$VER.jar:lib/log4j-core-$VER.jar"

rm -rf out && mkdir -p out bin
javac -cp "$CP" -d out src/*.java

cat > out/MANIFEST.MF <<MF
Manifest-Version: 1.0
Main-Class: Main
Class-Path: ../lib/log4j-api-$VER.jar ../lib/log4j-core-$VER.jar ../config/
Implementation-Title: Biblioteca
Implementation-Version: 1.0.0
MF

jar --create --file bin/biblioteca.jar --manifest out/MANIFEST.MF -C out Libro.class -C out Biblioteca.class -C out Main.class
echo "Generado: bin/biblioteca.jar"
