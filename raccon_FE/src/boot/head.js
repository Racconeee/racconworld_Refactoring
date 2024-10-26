import { createHead } from "@vueuse/head";

export default ({ app }) => {
  const head = createHead();
  app.use(head);
};
