import { useEffect, useState } from "react";
import api from "../api/api";

function EncadrantsEntreprise() {
  const [encadrants, setEncadrants] = useState([]);
  const [entreprises, setEntreprises] = useState([]);
  const [editingId, setEditingId] = useState(null);

  const [form, setForm] = useState({
    nom: "",
    prenom: "",
    email: "",
    telephone: "",
    entrepriseId: "",
  });

  const loadData = () => {
    api.get("/encadrants-entreprise")
      .then((response) => setEncadrants(response.data));

    api.get("/entreprises")
      .then((response) => setEntreprises(response.data));
  };

  useEffect(() => {
    loadData();
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
      entrepriseId: "",
    });
    setEditingId(null);
  };

  const buildData = () => ({
    nom: form.nom,
    prenom: form.prenom,
    email: form.email,
    telephone: form.telephone,
    entreprise: form.entrepriseId ? { id: Number(form.entrepriseId) } : null,
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = buildData();

    const request = editingId
      ? api.put(`/encadrants-entreprise/${editingId}`, data)
      : api.post("/encadrants-entreprise", data);

    request.then(() => {
      resetForm();
      loadData();
    });
  };

  const handleEdit = (encadrant) => {
    setEditingId(encadrant.id);
    setForm({
      nom: encadrant.nom || "",
      prenom: encadrant.prenom || "",
      email: encadrant.email || "",
      telephone: encadrant.telephone || "",
      entrepriseId: encadrant.entreprise?.id || "",
    });
  };

  const handleDelete = (id) => {
    if (window.confirm("Supprimer cet encadrant entreprise ?")) {
      api.delete(`/encadrants-entreprise/${id}`).then(() => loadData());
    }
  };

  return (
    <div>
      <h1>Encadrants entreprise</h1>

      <section className="panel">
        <h2>{editingId ? "Modifier un encadrant" : "Ajouter un encadrant"}</h2>

        <form className="form" onSubmit={handleSubmit}>
          <input name="nom" placeholder="Nom" value={form.nom} onChange={handleChange} required />
          <input name="prenom" placeholder="Prénom" value={form.prenom} onChange={handleChange} required />
          <input name="email" placeholder="Email" value={form.email} onChange={handleChange} required />
          <input name="telephone" placeholder="Téléphone" value={form.telephone} onChange={handleChange} />

          <select name="entrepriseId" value={form.entrepriseId} onChange={handleChange} required>
            <option value="">Choisir une entreprise</option>
            {entreprises.map((entreprise) => (
              <option key={entreprise.id} value={entreprise.id}>
                {entreprise.nom}
              </option>
            ))}
          </select>

          <button type="submit">{editingId ? "Modifier" : "Ajouter"}</button>

          {editingId && (
            <button type="button" className="secondary" onClick={resetForm}>
              Annuler
            </button>
          )}
        </form>
      </section>

      <section className="panel">
        <h2>Liste des encadrants entreprise</h2>

        <table>
          <thead>
            <tr>
              <th>Nom complet</th>
              <th>Email</th>
              <th>Téléphone</th>
              <th>Entreprise</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {encadrants.map((encadrant) => (
              <tr key={encadrant.id}>
                <td>{encadrant.nom} {encadrant.prenom}</td>
                <td>{encadrant.email}</td>
                <td>{encadrant.telephone}</td>
                <td>{encadrant.entreprise?.nom}</td>
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
                <td colSpan="5">Aucun encadrant entreprise trouvé.</td>
              </tr>
            )}
          </tbody>
        </table>
      </section>
    </div>
  );
}

export default EncadrantsEntreprise;