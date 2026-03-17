---
title: "Mi resolución de la máquina Movistar"
description: "mucho wifi 6 y todo lo que tú quieras, pero las contraseñas a plena vista, ¿no?"
author: "trevi"
pubDate: 2026-03-17
image:
  url: "/src/images/blog/writeup-ssh-movistar/banner.webp"
  alt: "Fragmento de código de una función de cifrado muy insegura encontrada en un archivo JavaScript"
tags: ["cybersec", "movistar"]
---

Hola. Estaba yo un lunes tan tranquilo configurando los puertos de mi router Wifi 6 Go (Askey RTF8225VW), cuando se me rompió la interfaz web de configuración y quería entrar al router por ssh, y decidí por curiosidad inspeccionar el login de la interfaz de configuración de mi router Movistar.

Al interceptar la solicitud y analizar los datos que se envían al router, pude ver que el usuario predeterminado, que suele ser "1234", se había transformado en unos caracteres diferentes.

![Captura de la solicitud de login](/src/images/blog/writeup-ssh-movistar/caido_request_login.webp)

Al descodificar el texto del username (url-decoding), obtenemos "`.-,+`". Este es el nombre de usuario cifrado, pero ¿por qué se ha cifrado? Fue entonces cuando decidí abrir las DevTools para inspeccionar la página y efectivamente, el `<input>` oculto del usuario estaba cifrado.

Entonces, usé Ctrl+U para navegar por los archivos <a href="https://es.wikipedia.org/wiki/Active_Server_Pages" target="_blank" rel="noopener">ASP</a> y encontrar posibles scripts responsables del cifrado y me topé con que la página de login importa un solo script de 1500 líneas que resulta que se usa en todo el panel. Busqué por alguna coincidencia de "pass" y encontré esta función:

```js
function mess_userpass(str) {
  return str
    .split("")
    .map(function (c) {
      return String.fromCharCode(c.charCodeAt(0) ^ 0x1f);
    })
    .join("");
}
```

¿Es en serio? Sí, completamente. Esta es la función que "cifra" el usuario y la contraseña antes de enviarla al servidor. Ni siquiera aplica MD5 ni nada así, hace una simple función XOR con cada carácter y el código hexadecimal `1f`. Esto es prácticamente como enviar una contraseña a través de la red en texto plano y si hay un Man in the Middle, adiós a tu red.

Después de lamentar mi existencia ante semejante bazofia de seguridad, estuve más tiempo inspeccionando esas 1500 líneas, y encontré comentarios como este:

```js
//This is modified by wangrong at 2007-12-27
```

Entonces me doy cuenta de que el problema es peor de lo que pensaba. Investigo más y empiezo a ver código para Internet Explorer, Netscape... Una cantidad inmensa de funciones innecesarias o incluso mal implementadas.

Algunos ejemplos:

```js
function $$(ele) {
  return typeof ele == "string" ? document.getElementById(ele) : ele;
}
```

Una comprobación para ver si estamos en un navegador "moderno"

```js
if (document.all || document.getElementById) {
  /* ... */
} else {
  /* ... */
}
```

Pero volviendo al tema original, lo que más me preocupa es este código:

```js
function countDown() {
  bruteTime--;
  if (bruteTime > 1) $("#bruteTime").text(bruteTime + " segundos");
  else $("#bruteTime").text(bruteTime + " segundo");

  if (bruteTime <= 0) {
    clearInterval(cid);

    $("#login :input").attr("disabled", false);
    $(".login_input,.Mlogin_input").attr("disabled", false);
    $(".bruteProtect").hide();
    $("[name=Password]").focus();
  }
}
if (bruteTime > 0) {
  $("#login :input").attr("disabled", true);
  $(".login_input,.Mlogin_input").attr("disabled", true);
  $("#bruteTime").text(bruteTime);
  $(".bruteProtect").show();
  cid = setInterval(countDown, 1000);
}
```

Este código se encuentra en el lado del cliente, cuando debería estar implementado en el servidor. Por si no se entiende, es una función que hace rate-limiting para que no puedas meter muchas contraseñas rápidamente y tengas que esperar entre cada solicitud. Pero al estar implementada en el cliente, me hace dudar porque también es posible enviar una solicitud POST al servidor y saltarse esa comprobación al menos en el lado del cliente.

El hecho de que el usuario y la contraseña se cifren con un XOR tan simple y reversible (por ejemplo, `mess_userpass(".-,+") devolvería "1234" el nombre de usuario original`) y que no hayan implementado un rate limit en el lado del servidor me parece un insulto a los usuarios por parte de Movistar y es una prueba de que no les importa nada la seguridad. Esto, junto con el hecho de que siguen manteniendo una bola de espagueti desde hace aproximadamente 20 años, es de lo peor que he visto en desarrollo de software. Totalmente inaceptable.
