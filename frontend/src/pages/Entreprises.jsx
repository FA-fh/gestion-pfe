import { useEffect, useState } from "react";
import api from "../api/api";

function Entreprises() {
  const [entreprises, setEntreprises] = useState([]);
  const [editingId, setEditingId] = useState(null);

  const [form, setForm] = useState({
    nom: "",
    adresse: "",
    telephone: "",
    email: "",
    ville: "",
    pays: "",
    responsableEmail: "",
  });

  const loadEntreprises = () => {
    api.get("/entreprises").then((response) => setEntreprises(response.data));
  };

  useEffect(() => {
    loadEntreprises();
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
      adresse: "",
      telephone: "",
      email: "",
      ville: "",
      pays: "",
      responsableEmail: "",
    });
    setEditingId(null);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const request = editingId
      ? api.put(`/entreprises/${editingId}`, form)
      : api.post("/entreprises", form);

    request.then(() => {
      resetForm();
      loadEntreprises();
    });
  };

  const handleEdit = (entreprise) => {
    setEditingId(entreprise.id);
    setForm({
      nom: entreprise.nom || "",
      adresse: entreprise.adresse || "",
      telephone: entreprise.telephone || "",
      email: entreprise.email || "",
      ville: entreprise.ville || "",
      pays: entreprise.pays || "",
      responsableEmail: entreprise.responsableEmail || "",
    });
  };

  const handleDelete = (id) => {
    if (window.confirm("Supprimer cette entreprise ?")) {
      api.delete(`/entreprises/${id}`).then(() => loadEntreprises());
    }
  };

  return (
    <div>
      <h1>Gestion des entreprises</h1>

      <section className="panel">
        <h2>{editingId ? "Modifier une entreprise" : "Ajouter une entreprise"}</h2>

        <form className="form" onSubmit={handleSubmit}>
          <input name="nom" placeholder="Nom" value={form.nom} onChange={handleChange} required />
          <input name="adresse" placeholder="Adresse" value={form.adresse} onChange={handleChange} />
          <input name="telephone" placeholder="Téléphone" value={form.telephone} onChange={handleChange} />
          <input name="email" placeholder="Email" value={form.email} onChange={handleChange} />
          <input name="ville" placeholder="Ville" value={form.ville} onChange={handleChange} />
          <input name="pays" placeholder="Pays" value={form.pays} onChange={handleChange} />
          <input
            name="responsableEmail"
            placeholder="Email du responsable"
            value={form.responsableEmail}
            onChange={handleChange}
          />

          <button type="submit">{editingId ? "Modifier" : "Ajouter"}</button>

          {editingId && (
            <button type="button" className="secondary" onClick={resetForm}>
              Annuler
            </button>
          )}
        </form>
      </section>

      <section className="panel">
        <h2>Liste des entreprises</h2>

        <table>
          <thead>
            <tr>
              <th>Nom</th>
              <th>Ville</th>
              <th>Pays</th>
              <th>Email</th>
              <th>Téléphone</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {entreprises.map((entreprise) => (
              <tr key={entreprise.id}>
                <td>{entreprise.nom}</td>
                <td>{entreprise.ville}</td>
                <td>{entreprise.pays}</td>
                <td>{entreprise.email}</td>
                <td>{entreprise.telephone}</td>
                <td>
                  <button onClick={() => handleEdit(entreprise)}>Modifier</button>
                  <button className="danger" onClick={() => handleDelete(entreprise.id)}>
                    Supprimer
                  </button>
                </td>
              </tr>
            ))}

            {entreprises.length === 0 && (
              <tr>
                <td colSpan="6">Aucune entreprise trouvée.</td>
              </tr>
            )}
          </tbody>
        </table>
      </section>
    </div>
  );
}

export default Entreprises;