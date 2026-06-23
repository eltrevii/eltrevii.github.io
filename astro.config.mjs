// @ts-check
import { defineConfig } from "astro/config";
import { satteri } from "@astrojs/markdown-satteri";

import sentry from "@sentry/astro";
import spotlightjs from "@spotlightjs/astro";

import tailwindcss from "@tailwindcss/vite";

import icon from "astro-icon";

process.env.ASTRO_TELEMETRY_DISABLED = "1";

// https://astro.build/config
export default defineConfig({
  trailingSlash: "always",
  site: "https://eltrevii.github.io",
  integrations: [
    // enable sentry and spotlight only on development
    ...(process.env.NODE_ENV === "development"
      ? [
          sentry({
            telemetry: false,
          }),
          spotlightjs(),
        ]
      : []),

    icon(),
  ],

  vite: {
    plugins: [tailwindcss()],
  },
  build: {
    assets: "assets",
  },
  markdown: {
    processor: satteri({
      features: { directive: true },
    }),
  },
});
