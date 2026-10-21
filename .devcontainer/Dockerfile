# ---------------------------------------------------------------------------
# Actividad: Instalación y configuración de Java SE Development Kit
# Estrategia: el JDK NO se instala en macOS. Vive dentro de este contenedor
#             que se ejecuta con Podman (rootless).
# ---------------------------------------------------------------------------

# Imagen base con OpenJDK 25 (LTS) — build oficial Eclipse Temurin
FROM docker.io/library/eclipse-temurin:25-jdk

ARG USERNAME=vscode
ARG USER_UID=1000
ARG USER_GID=1000

ENV DEBIAN_FRONTEND=noninteractive

# Herramientas base + dependencias que LazyVim necesita
RUN apt-get update && apt-get install -y --no-install-recommends \
        git curl wget unzip zip tar ca-certificates \
        build-essential ripgrep fd-find fzf \
        locales sudo gzip xz-utils \
        python3 python3-venv \
    && sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen && locale-gen \
    && ln -sf /usr/bin/fdfind /usr/local/bin/fd \
    && rm -rf /var/lib/apt/lists/*

ENV LANG=en_US.UTF-8 LC_ALL=en_US.UTF-8

# Node.js 22 (lo piden varios servidores LSP / Mason)
RUN ARCH="$(dpkg --print-architecture)" \
    && case "$ARCH" in arm64) NARCH=arm64 ;; amd64) NARCH=x64 ;; esac \
    && curl -fsSL "https://nodejs.org/dist/v22.14.0/node-v22.14.0-linux-${NARCH}.tar.xz" -o /tmp/node.tar.xz \
    && tar -xJf /tmp/node.tar.xz -C /usr/local --strip-components=1 \
    && rm /tmp/node.tar.xz

# Neovim (binario oficial, versión estable)
RUN ARCH="$(dpkg --print-architecture)" \
    && case "$ARCH" in arm64) NVARCH=arm64 ;; amd64) NVARCH=x86_64 ;; esac \
    && curl -fsSL "https://github.com/neovim/neovim/releases/download/v0.11.3/nvim-linux-${NVARCH}.tar.gz" -o /tmp/nvim.tar.gz \
    && tar -xzf /tmp/nvim.tar.gz -C /opt \
    && mv "/opt/nvim-linux-${NVARCH}" /opt/nvim \
    && ln -sf /opt/nvim/bin/nvim /usr/local/bin/nvim \
    && rm /tmp/nvim.tar.gz

# Maven y Gradle (build tools del ecosistema Java)
RUN apt-get update && apt-get install -y --no-install-recommends maven \
    && rm -rf /var/lib/apt/lists/*

# Usuario sin privilegios.
# La imagen base (Ubuntu) ya trae un usuario con UID/GID 1000: se libera antes.
RUN if getent passwd $USER_UID >/dev/null; then \
        userdel -r "$(getent passwd $USER_UID | cut -d: -f1)" 2>/dev/null || true; \
    fi \
    && if getent group $USER_GID >/dev/null; then \
        groupdel "$(getent group $USER_GID | cut -d: -f1)" 2>/dev/null || true; \
    fi \
    && groupadd --gid $USER_GID $USERNAME \
    && useradd --uid $USER_UID --gid $USER_GID -m -s /bin/bash $USERNAME \
    && echo "$USERNAME ALL=(root) NOPASSWD:ALL" > /etc/sudoers.d/$USERNAME \
    && chmod 0440 /etc/sudoers.d/$USERNAME \
    && id $USERNAME

# ---- Variables de entorno de Java (paso 4 de la actividad) ----
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH=$JAVA_HOME/bin:$PATH
ENV CLASSPATH=.

USER $USERNAME
WORKDIR /workspace

# LazyVim dentro del contenedor
RUN git clone --depth 1 https://github.com/LazyVim/starter /home/$USERNAME/.config/nvim \
    && rm -rf /home/$USERNAME/.config/nvim/.git

# Activa el "extra" de Java de LazyVim (nvim-jdtls + mason)
RUN printf '%s\n' \
  '{' \
  '  "extras": [' \
  '    "lazyvim.plugins.extras.lang.java"' \
  '  ],' \
  '  "install_version": 8,' \
  '  "news": {},' \
  '  "version": 8' \
  '}' > /home/$USERNAME/.config/nvim/lazyvim.json

# El workspace es un bind mount: su propietario difiere del usuario del
# contenedor, asi que se marca como directorio seguro para Git.
RUN git config --global --add safe.directory /workspace

CMD ["sleep", "infinity"]
