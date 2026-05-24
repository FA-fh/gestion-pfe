import { useEffect, useState } from "react";
import api from "../api/api";

function EncadrantsAcademiques() {
  const [encadrants, setEncadrants] = useState([]);
  const [editingId, setEditingId] = useState(null);

  const [form, setForm] = useState({
    nom: "",
    prenom: "",
    email: "",
    telephone: "",
    departement: "",
    etablissement: "",
  });

  const loadEncadrants = () => {
    api.get("/encadrants-academiques")
      .then((response) => setEncadrants(response.data));
  };

  useEffect(() => {
    loadEncadrants();
  }, []);

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const resetForm = () => {
    setForm({
      nom: "",
      prenom: "",
      email: "",
      telephone: "",
      departement: "",
      etablissement: "",
    });
    setEditingId(null);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const request = editingId
      ? api.put(`/encadrants-academiques/${editingId}`, form)
      : api.post("/encadrants-academiques", form);

    request.then(() => {
      resetForm();
      loadEncadrants();
    });
  };

  const handleEdit = (encadrant) => {
    setEditingId(encadrant.id);
    setForm({
      nom: encadrant.nom || "",
      prenom: encadrant.prenom || "",
      email: encadrant.email || "",
      telephone: encadrant.telephone || "",
      departement: encadrant.departement || "",
      etablissement: encadrant.etablissement || "",
    });
  };

  const handleDelete = (id) => {
    if (window.confirm("Supprimer cet encadrant académique ?")) {
      api.delete(`/encadrants-academiques/${id}`).then(() => loadEncadrants());
    }
  };

  return (
    <div>
      <h1>Encadrants académiques</h1>

      <section className="panel">
        <h2>{editingId ? "Modifier un encadrant" : "Ajouter un encadrant"}</h2>

        <form className="form" onSubmit={handleSubmit}>
          <input name="nom" placeholder="Nom" value={form.nom} onChange={handleChange} required />
          <input name="prenom" placeholder="Prénom" value={form.prenom} onChange={handleChange} required />
          <input name="email" placeholder="Email" value={form.email} onChange={handleChange} required />
          <input name="telephone" placeholder="Téléphone" value={form.telephone} onChange={handleChange} />
          <input name="departement" placeholder="Département" value={form.departement} onChange={handleChange} />
          <input name="etablissement" placeholder="Établissement" value={form.etablissement} onChange={handleChange} />

          <button type="submit">{editingId ? "Modifier" : "Ajouter"}</button>

          {editingId && (
            <button type="button" className="secondary" onClick={resetForm}>
              Annuler
            </button>
          )}
        </form>
      </section>

      <section className="panel">
        <h2>Liste des encadrants académiques</h2>

        <table>
          <thead>
            <tr>
              <th>Nom complet</th>
              <th>Email</th>
              <th>Téléphone</th>
              <th>Département</th>
              <th>Établissement</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {encadrants.map((encadrant) => (
              <tr key={encadrant.id}>
                <td>{encadrant.nom} {encadrant.prenom}</td>
                <td>{encadrant.email}</td>
                <td>{encadrant.telephone}</td>
                <td>{encadrant.departement}</td>
                <td>{encadrant.etablissement}</td>
                <td>
                  <button onClick={() => handleEdit(encadrant)}>Modifier</button>
                  <button className="danger" onClick={() => handleDelete(encadrant.id)}>
                    Supprimer
                  </button>
                </td>
              </tr>
            ))}

            {encadrants.length === 0 && (
              <tr>
                <td colSpan="6">Aucun encadrant académique trouvé.</td>
              </tr>
            )}
          </tbody>
        </table>
      </section>
    </div>
  );
}

export default EncadrantsAcademiques;