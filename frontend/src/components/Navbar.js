import { useNavigate } from "react-router-dom";

function Navbar() {
  const navigate = useNavigate();

  return (
    <div style={styles.nav}>
      <h2 style={{ color: "white" }}>Pharmacy</h2>

      <div style={styles.links}>
        <span onClick={() => navigate("/dashboard")}>Home</span>
        <span onClick={() => navigate("/medicines")}>Medicines</span>
        <span onClick={() => navigate("/orders")}>Orders</span>
        <span onClick={() => navigate("/inventory")}>Inventory</span>
        <span onClick={() => navigate("/prescriptions")}>Prescriptions</span>
      </div>
    </div>
  );
}

const styles = {
  nav: {
    display: "flex",
    justifyContent: "space-between",
    padding: "15px 30px",
    background: "linear-gradient(90deg, #667eea, #764ba2)",
  },
  links: {
    display: "flex",
    gap: "20px",
    color: "white",
    cursor: "pointer",
  },
};

export default Navbar;