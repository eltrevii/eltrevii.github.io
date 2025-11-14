// @ts-check
import { defineConfig } from 'astro/config';

import sentry from '@sentry/astro';
import spotlightjs from '@spotlightjs/astro';

import tailwindcss from '@tailwindcss/vite';

import icon from 'astro-icon';

process.env.ASTRO_TELEMETRY_DISABLED = '1';

// https://astro.build/config
export default defineConfig({
  trailingSlash: 'always',
  site: 'https://eltrevii.github.io',
  integrations: [sentry({
    telemetry: false
  }), spotlightjs(), icon()],

  vite: {
    plugins: [tailwindcss()]
  },
  build: {
    assets: 'assets'
  }
});
