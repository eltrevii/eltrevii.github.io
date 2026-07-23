function ensureStringArray(arg: string | string[]) {
  let finalarg: string[];
  if (typeof arg === "string") {
    finalarg = [arg];
  } else {
    finalarg = arg;
  }
  return finalarg;
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
