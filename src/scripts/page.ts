function ensureStringArray(arg: string | string[]): string[] {
  if (typeof arg === "string") {
    return [arg];
  } else {
    return arg;
  }
}

export function isPage(
  curPage: string,
  arg: string | string[],
): "page" | "false" {
  const finalarg = ensureStringArray(arg);
  for (var x of finalarg) {
    if (curPage === x) {
      return "page";
    }
  }
  return "false";
}

export function isSubPage(
  curPage: string,
  arg: string | string[],
): "page" | "false" {
  const finalarg = ensureStringArray(arg);
  for (var x of finalarg) {
    if (curPage.startsWith(x)) {
      return "page";
    }
  }
  return "false";
}
