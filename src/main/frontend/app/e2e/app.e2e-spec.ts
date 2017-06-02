import {AppNg4Page} from "./app.po";

describe('app-ng4 App', () => {
  let page: AppNg4Page;

  beforeEach(() => {
    page = new AppNg4Page();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
