import React, { useContext, useState } from "react";
import { Link, Redirect } from "react-router-dom";
import { Button, Card, CardHeader, CardBody, CardTitle, Form, Input, InputGroupAddon,
  InputGroupText, InputGroup, Container, Row, Col} from "reactstrap";
import { DataContext } from "store/GlobalState";
import { postData } from "utils/fetchData";

function RegisterPage() {

  const initialState = { nombre: '', apellido: '', dni: '', username: '', email: '', password: '' }
  const [userData, setUserData] = useState(initialState)

  const { state, dispatch } = useContext(DataContext)

  const { auth } = state

  const handleChangeInput = e => {
    const { name, value } = e.target
    setUserData({ ...userData, [name]: value })
  }

  const handleSubmit = async e => {
    e.preventDefault();
    
    await postData('registro/create', userData);

    dispatch({ type: 'NOTIFY', payload: { success: 'Registrado correctamente.' } })

    alert('OK!')

  }

  if (auth.user) return <Redirect to="/" />

  return (
    <Container>
      <Row className="justify-content-center">
        <Col md="6">            
          <Card className="card m-3" data-background-color="blue">
            <Form onSubmit={handleSubmit} className="form">
              <CardHeader className="text-center">
                <CardTitle className="title-up pt-2" tag="h3">
                  Registro
                </CardTitle>
                <div className="social-line">
                  <Button
                    className="btn-neutral btn-icon btn-round"
                    color="facebook"
                    href="#pablo"
                    onClick={(e) => e.preventDefault()}
                  >
                    <i className="fab fa-facebook-square"></i>
                  </Button>
                  <Button
                    className="btn-neutral btn-icon btn-round"
                    color="twitter"
                    href="#pablo"
                    onClick={(e) => e.preventDefault()}
                    size="lg"
                  >
                    <i className="fab fa-twitter"></i>
                  </Button>
                  <Button
                    className="btn-neutral btn-icon btn-round"
                    color="google"
                    href="#pablo"
                    onClick={(e) => e.preventDefault()}
                  >
                    <i className="fab fa-google-plus"></i>
                  </Button>
                </div>
              </CardHeader>
              <CardBody>
                <Row>
                  <Col md="6">
                    <InputGroup className="no-border">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="now-ui-icons users_circle-08"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="Ingrese su nombre"
                        type="text"
                        name="nombre"
                        value={userData.nombre}
                        onChange={handleChangeInput}
                      ></Input>
                    </InputGroup>
                  </Col>
                  <Col md="6">
                    <InputGroup className="no-border">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="now-ui-icons text_caps-small"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="Ingrese su apellido"
                        type="text"
                        name="apellido"
                        value={userData.apellido}
                        onChange={handleChangeInput}
                      ></Input>
                    </InputGroup>
                  </Col>
                </Row>
                <Row>
                  <Col md="6">
                    <InputGroup className="no-border">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="now-ui-icons ui-1_email-85"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="Ingrese su email"
                        type="text"
                        name="email"
                        value={userData.email}
                        onChange={handleChangeInput}
                      ></Input>
                    </InputGroup>
                  </Col>
                  <Col md="6">
                    <InputGroup className="no-border">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="now-ui-icons text_caps-small"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="Ingrese su DNI"
                        type="text"
                        name="dni"
                        value={userData.dni}
                        onChange={handleChangeInput}
                      ></Input>
                    </InputGroup>
                  </Col>
                </Row>
                <Row>
                  <Col md="6">
                    <InputGroup className="no-border">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="now-ui-icons users_circle-08"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="Ingrese un username"
                        type="text"
                        name="username"
                        value={userData.username}
                        onChange={handleChangeInput}
                      ></Input>
                    </InputGroup>
                  </Col>
                  <Col md="6">
                    <InputGroup className="no-border">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="now-ui-icons ui-1_email-85"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="Ingrese una constraseña"
                        type="password"
                        name="password"
                        value={userData.password}
                        onChange={handleChangeInput}
                      ></Input>
                    </InputGroup>
                  </Col>
                </Row>
                <Button
                  className="btn-neutral btn-round w-100"
                  size="lg"
                  type="submit"
                >
                  Registrarse
                </Button>
                <h6 className="text-center mb-4">
                  <Link
                    className="link"
                    to="/login"
                  >
                    Login Now
                  </Link>
                </h6>
              </CardBody>
            </Form>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}

export default RegisterPage;
