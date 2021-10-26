let initialState = {
  cart: [],
};

function reducer(store = initialState, action) {
  switch (action.type) {
    case "addItemToCart":
      if (!action.payload) {
        return null;
      }
      return { ...store, cart: [...store.cart, action.payload] };
    default:
      return store;
  }
}

export default reducer;