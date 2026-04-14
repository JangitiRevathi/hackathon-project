import { useState } from "react";
import axios from "axios";

function Inventory() {
  const [id, setId] = useState("");
  const [stock, setStock] = useState(null);
  const [quantity, setQuantity] = useState("");

  const getStock = async () => {
    const res = await axios.get(`http://localhost:8081/api/inventory/${id}`);
    setStock(res.data);
  };

  const updateStock = async () => {
    await axios.put(`http://localhost:8081/api/inventory/${id}?quantity=${quantity}`);
    alert("Stock updated ✅");
  };

  const lowStock = async () => {
    const res = await axios.get("http://localhost:8081/api/inventory/low-stock");
    setStock(res.data);
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Inventory</h2>

      <input placeholder="Medicine ID" onChange={e => setId(e.target.value)} />
      <input placeholder="Quantity" onChange={e => setQuantity(e.target.value)} />

      <br /><br />

      <button onClick={getStock}>Get Stock</button>
      <button onClick={updateStock}>Update Stock</button>
      <button onClick={lowStock}>Low Stock</button>

      <pre>{JSON.stringify(stock, null, 2)}</pre>
    </div>
  );
}

export default Inventory;