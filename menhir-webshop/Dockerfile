# ---------- Build Stage ----------
FROM node:20-alpine AS build

WORKDIR /app

# Abhängigkeiten installieren
COPY package*.json ./
RUN npm ci

# Source kopieren & Build
COPY . .
RUN npm run build


# ---------- Runtime Stage ----------
FROM nginx:alpine

# Default Nginx Config entfernen
RUN rm /etc/nginx/conf.d/default.conf

# Eigene Nginx Config (optional, aber empfohlen)
COPY nginx.conf /
