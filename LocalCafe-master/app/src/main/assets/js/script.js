const getCookieValue = (name) =>
  document.cookie.match("(^|;)\\s*" + name + "\\s*=\\s*([^;]+)")?.pop() || "";

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const table_id = urlParams.get("table");
window.history.pushState("/", "", "/");

const tableInput = document.querySelector("#table_id");
if (table_id != undefined) {
  tableInput.value = table_id;
  tableInput.readOnly = true;
  document.cookie = `table=${table_id}; expires=Thu, 18 Dec 2199 12:00:00 UTC; path=/`;
} else {
  let value = getCookieValue("table");
  if (value != undefined) {
    value = parseInt(value);
  }
  if (!isNaN(value)) {
    tableInput.value = value;
    tableInput.readOnly = true;
  } else tableInput.readOnly = false;
}
const items = {
  coffee: {},
  nescafe: {},
  capochino: {},
  choco: {},
};

items.coffee.min = document.querySelector("#coffee_btn_min");
items.coffee.plus = document.querySelector("#coffee_btn_plus");
items.coffee.input = document.querySelector("#coffee");

items.nescafe.min = document.querySelector("#nescafe_btn_min");
items.nescafe.plus = document.querySelector("#nescafe_btn_plus");
items.nescafe.input = document.querySelector("#nescafe");

items.capochino.min = document.querySelector("#capochino_btn_min");
items.capochino.plus = document.querySelector("#capochino_btn_plus");
items.capochino.input = document.querySelector("#capochino");

items.choco.min = document.querySelector("#choco_btn_min");
items.choco.plus = document.querySelector("#choco_btn_plus");
items.choco.input = document.querySelector("#choco");

for (const key in items) {
  if (Object.hasOwnProperty.call(items, key)) {
    const drinkItem = items[key];

    drinkItem.min.addEventListener("click", () => {
      let i = parseInt(drinkItem.input.value);
      if (i > 0) {
        drinkItem.input.value = i - 1;
      }
    });
    drinkItem.plus.addEventListener("click", () => {
      let i = parseInt(drinkItem.input.value);
      if (i < 10) {
        drinkItem.input.value = i + 1;
      }
    });
  }
}
