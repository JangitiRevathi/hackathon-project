import { useEffect, useState } from "react";
import axios from "axios";

function Prescriptions() {
  const [data, setData] = useState([]);
  const [text, setText] = useState("");

  const load = () => {
    axios.get("http://localhost:8081/prescriptions")
      .then(res => setData(res.data));
  };

  useEffect(() => {
    load();
  }, []);

  const create = async () => {
    await axios.post("http://localhost:8081/prescriptions", {
      description: text
    });
    load();
  };

  const remove = async (id) => {
    await axios.delete(`http://localhost:8081/prescriptions/${id}`);
    load();
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Prescriptions</h2>

      <input placeholder="Prescription" onChange={e => setText(e.target.value)} />
      <button onClick={create}>Add</button>

      {data.map((p) => (
        <div key={p.id} style={card}>
          <p>{p.description}</p>
          <button onClick={() => remove(p.id)}>Delete</button>
        </div>
      ))}
    </div>
  );
}

const card = {
  border: "1px solid #ccc",
  padding: "10px",
  margin: "10px",
};

export default Prescriptions;