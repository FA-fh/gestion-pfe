import { useEffect, useState } from "react";
import api from "../api/api";

function Filieres() {
  const [filieres, setFilieres] = useState([]);
  const [intitule, setIntitule] = useState("");
  const [editingId, setEditingId] = useState(null);

  const loadFilieres = () => {
    api.get("/filieres")
      .then((response) => setFilieres(response.data))
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    loadFilieres();
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = { intitule };

    const request = editingId
      ? api.put(`/filieres/${editingId}`, data)
      : api.post("/filieres", data);

    request.then(() => {
      setIntitule("");
      setEditingId(null);
      loadFilieres();
    });
  };

  const handleEdit = (filiere) => {
    setIntitule(filiere.intitule);
    setEditingId(filiere.id);
  };

  const handleDelete = (id) => {
    if (window.confirm("Supprimer cette filière ?")) {
      api.delete(`/filieres/${id}`).then(() => loadFilieres());
    }
  };

  return (
    <div>
      <h1>Gestion des filières</h1>

      <section className="panel">
        <h2>{editingId ? "Modifier une filière" : "Ajouter une filière"}</h2>

        <form className="form" onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Intitulé de la filière"
            value={intitule}
            onChange={(e) => setIntitule(e.target.value)}
            required
          />

          <button type="submit">
            {editingId ? "Modifier" : "Ajouter"}
          </button>

          {editingId && (
            <button
              type="button"
              className="secondary"
              onClick={() => {
                setEditingId(null);
                setIntitule("");
              }}
            >
              Annuler
            </button>
          )}
        </form>
      </section>

      <section className="panel">
        <h2>Liste des filières</h2>

        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Intitulé</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {filieres.map((filiere) => (
              <tr key={filiere.id}>
                <td>{filiere.id}</td>
                <td>{filiere.intitule}</td>
                <td>
                  <button onClick={() => handleEdit(filiere)}>
                    Modifier
                  </button>
                  <button
                    className="danger"
                    onClick={() => handleDelete(filiere.id)}
                  >
                    Supprimer
                  </button>
                </td>
              </tr>
            ))}

            {filieres.length === 0 && (
              <tr>
                <td colSpan="3">Aucune filière trouvée.</td>
              </tr>
            )}
          </tbody>
        </table>
      </section>
    </div>
  );
}

export default Filieres;