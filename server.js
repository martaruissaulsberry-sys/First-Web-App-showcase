const express = require('express');
const path = require('path');
const cors = require('cors');
const Stripe = require('stripe');
require('dotenv').config();

const app = express();
const stripeSecretKey = process.env.STRIPE_SECRET_KEY || 'sk_test_YOUR_SECRET_KEY';
const stripe = Stripe(stripeSecretKey);

app.use(cors());
app.use(express.json());
app.use(express.static(path.join(__dirname)));

app.post('/create-checkout-session', async (req, res) => {
  const { items } = req.body;

  if (!items || !Array.isArray(items) || items.length === 0) {
    return res.status(400).json({ error: 'No items provided' });
  }

  try {
    const session = await stripe.checkout.sessions.create({
      payment_method_types: ['card'],
      mode: 'payment',
      line_items: items,
      success_url: `${req.protocol}://${req.get('host')}/success`,
      cancel_url: `${req.protocol}://${req.get('host')}/cancel`,
    });

    res.json({ sessionId: session.id });
  } catch (error) {
    console.error('Stripe checkout session error:', error);
    res.status(500).json({ error: 'Unable to create checkout session.' });
  }
});

app.get('/success', (req, res) => {
  res.send(`<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Payment Successful</title></head><body style="font-family:Arial,sans-serif; text-align:center; margin:50px;"><h1>Payment Successful</h1><p>Thank you for your purchase!</p><p><a href="/DavisFruitShop.html">Return to shop</a></p></body></html>`);
});

app.get('/cancel', (req, res) => {
  res.send(`<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Payment Canceled</title></head><body style="font-family:Arial,sans-serif; text-align:center; margin:50px;"><h1>Payment Canceled</h1><p>Your payment was not completed.</p><p><a href="/DavisFruitShop.html">Return to shop</a></p></body></html>`);
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});
