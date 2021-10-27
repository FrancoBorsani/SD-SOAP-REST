import React from "react";

import {
  Button,
  Card,
  CardBody,
  Form,
  Input,
  InputGroupAddon,
  InputGroupText,
  InputGroup,
  Container,
  Col,
  CardTitle,
  Row,
  CardHeader
} from "reactstrap";

import { Link } from "react-router-dom";

function LoginPage() {
  const [firstFocus, setFirstFocus] = React.useState(false);
  const [lastFocus, setLastFocus] = React.useState(false);

  return (
    <Container>
      <Row className="justify-content-center">
        <Col md="6">
          <Card className="card m-3" data-background-color="blue">
            <Form action="" className="form" method="">
              <CardHeader className="text-center">
                <CardTitle className="title-up pt-2" tag="h3">
                  Login
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
                  <Col md="12">
                    <InputGroup className="no-border"
                    >
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="now-ui-icons users_circle-08"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="Ingrese su nombre"
                        type="text"
                      ></Input>
                    </InputGroup>
                    <InputGroup className="no-border">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="now-ui-icons text_caps-small"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="Ingrese su apellido"
                        type="text"
                      ></Input>
                    </InputGroup>
                  </Col>
                </Row>
                <Button
                  className="btn-neutral btn-round w-100"
                  color="info"
                  href="#pablo"
                  onClick={(e) => e.preventDefault()}
                  size="lg"
                >
                  Login
                </Button>
                <h6 className="text-center mb-4">
                  <Link
                    className="link"
                    to="/registro"
                  >
                    Registrarse
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

export default LoginPage;
