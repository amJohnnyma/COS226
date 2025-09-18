
import pandas as pd
import matplotlib.pyplot as plt

# Load data
df = pd.read_csv("const_results.csv")

#Plot 1: Time vs Players (separate per contention level)
for contention in df["Contention"].unique():
    subset = df[df["Contention"] == contention]
    plt.figure()
    for lock in subset["Lock"].unique():
        sub2 = subset[subset["Lock"] == lock]
        plt.plot(sub2["Players"], sub2["Time"], marker="o", label=lock)
    plt.title(f"Execution Time vs Players ({contention} Contention)")
    plt.xlabel("Players")
    plt.ylabel("Time (ms)")
    plt.legend()
    plt.grid(True)
    plt.savefig(f"const_plot_time_{contention}.png")
    plt.close()

# Plot 2: Fairness spread (Max - Min) vs Players
df["Spread"] = df["Max"] - df["Min"]

for contention in df["Contention"].unique():
    subset = df[df["Contention"] == contention]
    plt.figure()
    for lock in subset["Lock"].unique():
        sub2 = subset[subset["Lock"] == lock]
        plt.plot(sub2["Players"], sub2["Spread"], marker="o", label=lock)
    plt.title(f"Fairness Spread (Max-Min) vs Players ({contention} Contention)")
    plt.xlabel("Players")
    plt.ylabel("Spread (Coins difference)")
    plt.legend()
    plt.grid(True)
    plt.savefig(f"const_plot_fairness_{contention}.png")
    plt.close()
