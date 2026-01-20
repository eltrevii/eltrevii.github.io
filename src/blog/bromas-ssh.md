---
title: "cuidado con dar acceso ssh a cualquiera, a menos que te quieras comer estas bromas"
description: "controlar un ordenador remotamente no es una broma... ¿o sí?"
author: "trevi"
pubDate: 2026-01-20
image:
  url: "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2F3.bp.blogspot.com%2F-lbWFOuJyPgQ%2FUdywf-CXBbI%2FAAAAAAAAAJg%2F0c1kSEIujJE%2Fs1600%2Fimage003.png&f=1&nofb=1&ipt=0b4e306ec19f2e82c3aa43ef4382690bab21eb1292ce46791be8436f8758c8b0º"
  alt: "imagen de una terminal con comandos en color verde"
tags: ["ssh", "bromas"]
---

Hola. Si en algún momento has tratado de conectarte a un ordenador de forma remota a través de la terminal seguramente conozcas el comando `ssh`. El funcionamiento es muy sencillo: nos conectamos a través de la dirección IP del ordenador "destino", y lo controlamos.
Pero si damos acceso a cualquier persona, puede pasar cualquier cosa, especialmente si el usuario se encuentra en la lista de "sudoers" (en Unix/Linux). Es por eso que por defecto el puerto 22/tcp (o puerto 22 en el protocolo TCP) se encuentra bloqueado en sistemas Linux que usen `ufw` como Firewall, por ejemplo. Por ese motivo tenemos que ejecutar `ufw allow ssh` o `ufw allow 22/tcp`.

Cuando un sistema se encuentra desprotegido, como puede ser el caso de una máquina virtual con el puerto SSH y credenciales expuestas, a algunos nos gusta toquetear con el sistema, gastar alguna broma o realmente hacer lo que nos da la gana, pero a mí me gusta más bromear un poco, hacer cosas como las siguientes:

---

## 1. Cambio de resolución

Esta es bastante interesante y puede asustar a cualquiera que esté usando la máquina en el momento.

Cualquiera que tenga unas nociones básicas de Linux debería conocer el comando `xrandr`. Es un programa que nos permite cambiar la resolución de nuestras pantallas de forma conveniente desde la terminal. Un problema de esto es que `xrandr` y otros programas que dependan de pantallas no se pueden usar desde una terminal a la ligera. Si intentamos usarlo desde una `tty`, o desde `ssh` veremos que no pasa nada:

```shell
$ xrandr
Can't open display
```

Como podemos ver, nos indica que no puede "abrir" la pantalla, o dicho de otra manera, que no puede interactuar con nuestro monitor. ¿Por qué pasa esto? Porque normalmente en Linux, cuando abrimos una terminal en un monitor, ya sea una `tty` o una ventana en el escritorio, se establece automáticamente la variable `$DISPLAY`. Esto permite que los programas que necesiten interactuar con los monitores sepan qué pantalla usar. Debo admitir que no sé mucho sobre el funcionamiento de las pantallas y cómo las gestiona Linux, pero lo único que debemos saber por ahora es que `xrandr` necesita esta variable para funcionar.

Si inspeccionamos esta variable en un sistema de Linux normal desde un entorno de escritorio, obtendremos un resultado como el siguiente:

```shell
$ echo $DISPLAY
:0.0
```

Entonces sabemos que debemos tener una variable llamada `$DISPLAY` con el valor `:0.0` (que también se puede indicar como `:0`) durante la ejecución de `xrandr`.

Normalmente, intentaríamos hacer algo como:

```shell
$ DISPLAY=:0
$ xrandr
```

Pero de nuevo, obtendríamos el error de `Can't open display`. Esto se debe a la forma en la que funcionan las variables en Linux, pero no entraré mucho en detalle. En su lugar, podemos usar una de estas dos opciones:

```shell
$ DISPLAY=:0 xrandr
```

o

```shell
$ export DISPLAY=:0
$ xrandr
```

Si usamos esta última opción podemos ejecutar `xrandr` las veces que queramos, y no nos dará error, aunque algunos prefieren usar la primera opción. Es cuestión de preferencia personal, pero por simplicidad vamos a usar el método de `export`.

De nuevo, si ejecutamos `xrandr`, veremos que nos lista una cantidad inmensa de configuraciones/resoluciones de pantalla, que en el caso de este programa se denominan "modos".

```shell
$ xrandr
Screen 0: minimum 1 x 1, current 1718 x 928, maximum 16384 x 16384
Virtual1 connected 1718x928+0+0 (normal left inverted right x axis y axis) 0mm x 0mm
   1718x928      60.00*+
   4096x2160     60.00    59.94
   2560x1600     59.99    59.97
   1920x1440     60.00
   1856x1392     60.00
   1792x1344     60.00
   2048x1152     60.00
   1920x1200     59.88    59.95
   1920x1080     60.00
   1600x1200     60.00
   1680x1050     59.95    59.88
   1400x1050     59.98    59.95
   1600x900      60.00
   1280x1024     60.02
   1440x900      59.89    59.90
   1280x960      60.00
   1366x768      59.79    60.00
   1360x768      60.02
   1280x800      59.81    59.91
   1280x768      59.87    59.99
   1280x720      60.00
   1024x768      60.00
   800x600       60.32    56.25
   848x480       60.00
   640x480       59.94
Virtual2 disconnected (normal left inverted right x axis y axis)
Virtual3 disconnected (normal left inverted right x axis y axis)
Virtual4 disconnected (normal left inverted right x axis y axis)
Virtual5 disconnected (normal left inverted right x axis y axis)
Virtual6 disconnected (normal left inverted right x axis y axis)
Virtual7 disconnected (normal left inverted right x axis y axis)
Virtual8 disconnected (normal left inverted right x axis y axis)
```

En este caso, al estar en una máquina virtual me aparecen muchas más configuraciones posibles, y otras 7 pantallas virtuales además de la principal. Pero si nos fijamos, podemos ver que en la segunda linea dice:

```shell
Virtual1 connected #...
```

Este es el nombre del "output" que vamos a usar en los comandos. Cuando aparezca `<output>` en los comandos que aparezcan a partir de ahora, deberemos reemplazarlo con lo que haya aparecido en esa linea, en este caso será `Virtual1` pero en máquinas reales será un valor diferente.

Ahora, comenzaremos a analizar las resoluciones que nos aparecen. Por ejemplo, podemos probar con la más baja, que es la siguiente:

```shell
   640x480       59.94
```

Podríamos crear nuestras propias resoluciones también, pero elegiremos esta por ahora porque es la predeterminada, y la pondremos con este comando:

```shell
$ xrandr --output <output> --mode 640x480
```

Así podemos bromear a quien esté usando la máquina, cambiando su resolución de pantalla de repente. Es bastante gracioso, aunque también podemos probar más cosas...

---

## 2. Fondo de pantalla

Sí, es posible cambiar el fondo de pantalla desde la terminal, y también desde SSH. Pero debido a motivos personales, no pude terminar de escribir esta publicación a tiempo, y también preferiría dejarlo así durante un tiempo, porque sé que esta publicación se leerá en mi clase y conozco bien a los especímenes que se encuentran en ella. No, gracias.
