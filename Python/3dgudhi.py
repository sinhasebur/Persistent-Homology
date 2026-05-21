import gudhi
import numpy as np
import matplotlib.pyplot as plt

file_path = "in.txt"

points = np.loadtxt(file_path, delimiter=None)


print("Loaded points shape:", points.shape)

fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

ax.scatter(points[:,0], points[:,1], points[:,2])
ax.set_title("3D Point Cloud")

plt.savefig("point_cloud_3d.png")
plt.close()

rips_complex = gudhi.RipsComplex(
    points=points,
    max_edge_length=2.0
)

simplex_tree = rips_complex.create_simplex_tree(max_dimension=3)

persistence = simplex_tree.persistence()

print(persistence)

filtered = []

threshold = 0.15

for dim, (birth, death) in persistence:

    if death == float("inf"):
        filtered.append((dim, (birth, death)))

    elif death - birth > threshold:
        filtered.append((dim, (birth, death)))

gudhi.plot_persistence_diagram(filtered)

plt.title("Persistence Diagram")
plt.savefig("diagram_3d.png")
plt.close()


print("\nImportant features:")

for dim, (birth, death) in filtered:

    if death == float("inf"):
        pers = "inf"
    else:
        pers = round(death - birth, 3)

    print(f"H{dim}: birth={birth:.3f}, death={death}, persistence={pers}")
