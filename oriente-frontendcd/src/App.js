import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

const API_BASE_URL = 'http://localhost:8080/api';

function App() {
  const [users, setUsers] = useState([]);
  const [projects, setProjects] = useState([]);
  const [activeTab, setActiveTab] = useState('users');
  const [loading, setLoading] = useState(false);

  // Estados para formulários
  const [newUser, setNewUser] = useState({
    name: '',
    email: '',
    password: '',
    role: 'USER'
  });

  const [newProject, setNewProject] = useState({
    name: '',
    description: ''
  });

  // Carregar dados quando o componente monta
  useEffect(() => {
    loadUsers();
    loadProjects();
  }, []);

  // Função para carregar usuários
  const loadUsers = async () => {
    try {
      setLoading(true);
      const response = await axios.get(`${API_BASE_URL}/users`);
      setUsers(response.data);
    } catch (error) {
      console.error('Erro ao carregar usuários:', error);
      alert('Erro ao carregar usuários. Verifique se o backend está rodando!');
    } finally {
      setLoading(false);
    }
  };

  // Função para carregar projetos
  const loadProjects = async () => {
    try {
      setLoading(true);
      const response = await axios.get(`${API_BASE_URL}/projects`);
      setProjects(response.data);
    } catch (error) {
      console.error('Erro ao carregar projetos:', error);
      alert('Erro ao carregar projetos. Verifique se o backend está rodando!');
    } finally {
      setLoading(false);
    }
  };

  // Função para criar usuário
  const createUser = async (e) => {
    e.preventDefault();

    // Preparar dados do usuário com hash da senha
    const userData = {
      name: newUser.name,
      email: newUser.email,
      passwordHash: newUser.password, // Backend vai fazer o hash
      role: newUser.role
    };

    try {
      await axios.post(`${API_BASE_URL}/users`, userData);
      alert('Usuário criado com sucesso!');
      setNewUser({ name: '', email: '', password: '', role: 'USER' });
      loadUsers(); // Recarregar lista
    } catch (error) {
      console.error('Erro ao criar usuário:', error);
      alert('Erro ao criar usuário!');
    }
  };

  // Função para criar projeto
  const createProject = async (e) => {
    e.preventDefault();
    if (users.length === 0) {
      alert('Crie pelo menos um usuário primeiro para ser o dono do projeto!');
      return;
    }

    const projectWithOwner = {
      ...newProject,
      owner: users[0] // Primeiro usuário como dono (simplificado)
    };

    try {
      await axios.post(`${API_BASE_URL}/projects`, projectWithOwner);
      alert('Projeto criado com sucesso!');
      setNewProject({ name: '', description: '' });
      loadProjects(); // Recarregar lista
    } catch (error) {
      console.error('Erro ao criar projeto:', error);
      alert('Erro ao criar projeto!');
    }
  };

  // Função para deletar usuário
  const deleteUser = async (id) => {
    if (window.confirm('Tem certeza que deseja deletar este usuário?')) {
      try {
        await axios.delete(`${API_BASE_URL}/users/${id}`);
        alert('Usuário deletado com sucesso!');
        loadUsers();
      } catch (error) {
        console.error('Erro ao deletar usuário:', error);
        alert('Erro ao deletar usuário!');
      }
    }
  };

  // Função para deletar projeto
  const deleteProject = async (id) => {
    if (window.confirm('Tem certeza que deseja deletar este projeto?')) {
      try {
        await axios.delete(`${API_BASE_URL}/projects/${id}`);
        alert('Projeto deletado com sucesso!');
        loadProjects();
      } catch (error) {
        console.error('Erro ao deletar projeto:', error);
        alert('Erro ao deletar projeto!');
      }
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>🎯 Oriente - Teste Frontend</h1>
        <nav>
          <button
            className={activeTab === 'users' ? 'active' : ''}
            onClick={() => setActiveTab('users')}
          >
            👥 Usuários ({users.length})
          </button>
          <button
            className={activeTab === 'projects' ? 'active' : ''}
            onClick={() => setActiveTab('projects')}
          >
            📁 Projetos ({projects.length})
          </button>
        </nav>
      </header>

      <main className="main-content">
        {loading && <div className="loading">⏳ Carregando...</div>}

        {activeTab === 'users' && (
          <div className="section">
            <h2>👥 Gerenciar Usuários</h2>

            {/* Formulário para criar usuário */}
            <form onSubmit={createUser} className="form">
              <h3>➕ Criar Novo Usuário</h3>
              <input
                type="text"
                placeholder="Nome"
                value={newUser.name}
                onChange={(e) => setNewUser({...newUser, name: e.target.value})}
                required
              />
              <input
                type="email"
                placeholder="Email"
                value={newUser.email}
                onChange={(e) => setNewUser({...newUser, email: e.target.value})}
                required
              />
              <input
                type="password"
                placeholder="Senha"
                value={newUser.password}
                onChange={(e) => setNewUser({...newUser, password: e.target.value})}
                required
                minLength="6"
              />
              <select
                value={newUser.role}
                onChange={(e) => setNewUser({...newUser, role: e.target.value})}
              >
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
              </select>
              <button type="submit">Criar Usuário</button>
            </form>

            {/* Lista de usuários */}
            <div className="list">
              <h3>📋 Lista de Usuários</h3>
              {users.length === 0 ? (
                <p>Nenhum usuário encontrado. Crie o primeiro!</p>
              ) : (
                users.map(user => (
                  <div key={user.id} className="item">
                    <div>
                      <strong>{user.name}</strong><br/>
                      📧 {user.email}<br/>
                      🏷️ {user.role}
                    </div>
                    <button
                      onClick={() => deleteUser(user.id)}
                      className="delete-btn"
                    >
                      🗑️ Deletar
                    </button>
                  </div>
                ))
              )}
            </div>
          </div>
        )}

        {activeTab === 'projects' && (
          <div className="section">
            <h2>📁 Gerenciar Projetos</h2>

            {/* Formulário para criar projeto */}
            <form onSubmit={createProject} className="form">
              <h3>➕ Criar Novo Projeto</h3>
              <input
                type="text"
                placeholder="Nome do Projeto"
                value={newProject.name}
                onChange={(e) => setNewProject({...newProject, name: e.target.value})}
                required
              />
              <textarea
                placeholder="Descrição do Projeto"
                value={newProject.description}
                onChange={(e) => setNewProject({...newProject, description: e.target.value})}
                rows="3"
              />
              <button type="submit">Criar Projeto</button>
              {users.length === 0 && (
                <p className="warning">⚠️ Crie um usuário primeiro!</p>
              )}
            </form>

            {/* Lista de projetos */}
            <div className="list">
              <h3>📋 Lista de Projetos</h3>
              {projects.length === 0 ? (
                <p>Nenhum projeto encontrado. Crie o primeiro!</p>
              ) : (
                projects.map(project => (
                  <div key={project.id} className="item">
                    <div>
                      <strong>{project.name}</strong><br/>
                      📝 {project.description}<br/>
                      👤 Dono: {project.owner?.name || 'N/A'}
                    </div>
                    <button
                      onClick={() => deleteProject(project.id)}
                      className="delete-btn"
                    >
                      🗑️ Deletar
                    </button>
                  </div>
                ))
              )}
            </div>
          </div>
        )}
      </main>
    </div>
  );
}

export default App;